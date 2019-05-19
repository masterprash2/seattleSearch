package com.homeaway.gatewayimpl

import android.content.Context
import com.homeaway.gateway.FavoriteGateway
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.io.*
import java.util.concurrent.Executors
import javax.inject.Inject

class FilePersistentFavoriteGateway @Inject constructor(private val context: Context) : FavoriteGateway {

    private val elements = HashSet<String>()
    private val addToFaovritePublisher = PublishSubject.create<String>()
    private val removeFromFaovritePublisher = PublishSubject.create<String>()
    private val executor = Executors.newSingleThreadExecutor()

    private val favoriteFile = File(context.getDir("favorites", Context.MODE_PRIVATE), "favorites.txt")

    init {
        executor.submit { readFile() }
    }

    private fun readFile() {
        if (favoriteFile.exists()) {
            var br: BufferedReader? = null
            try {
                br = BufferedReader(FileReader(favoriteFile))
                var line: String? = null
                do {
                    line = br.readLine()
                    if (!line.isNullOrEmpty()) {
                        elements.add(line)
                    }
                } while (line != null)

            } catch (e: java.lang.Exception) {
                try {
                    favoriteFile.delete()
                } catch (ee: Exception) {
                }
            } finally {
                try {
                    br?.close()
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun removeFromFavorites(venueId: String) {
        removeFromFaovritePublisher.onNext(venueId)
        updateToFile(venueId, false)
    }


    override fun addToFavorites(venueId: String) {
        addToFaovritePublisher.onNext(venueId)
        updateToFile(venueId, true)
    }

    override fun venueFavoriteUpdates(venueId: String): Observable<Boolean> {
        val addObserver = addToFaovritePublisher.filter { it.equals(venueId) }
            .map { true }
        val removeObserver = removeFromFaovritePublisher.filter { it.equals(venueId) }.map { false }
        return addObserver.mergeWith(removeObserver).mergeWith(readCurrentStatusForVenue(venueId))

    }

    private fun readCurrentStatusForVenue(venueId: String): Observable<Boolean> {
        val publisher = BehaviorSubject.create<Boolean>()
        executor.submit {
            publisher.onNext(elements.contains(venueId))
        }
        return publisher
    }

    private fun updateToFile(venueId: String, add: Boolean) {
        executor.submit {
            if (add)
                elements.add(venueId)
            else {
                elements.remove(venueId)
            }
            if (favoriteFile.exists()) {
                favoriteFile.delete()
            }
            favoriteFile.createNewFile()
            var fw: BufferedWriter? = null
            try {
                fw = BufferedWriter(FileWriter(favoriteFile))
                for (id in elements) {
                    fw.write(id)
                    fw.newLine()
                }
                fw.flush()
            } finally {
                try {
                    fw?.close()
                } catch (e: java.lang.Exception) {
                }
            }
        }

    }


}