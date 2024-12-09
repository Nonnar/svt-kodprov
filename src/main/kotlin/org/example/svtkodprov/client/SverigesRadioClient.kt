package org.example.svtkodprov.client

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.example.svtkodprov.model.EpisodeModel
import org.example.svtkodprov.model.EpisodesResponse
import org.example.svtkodprov.model.ProgramModel
import org.example.svtkodprov.model.ProgramsResponse

class SverigesRadioClient {

    private var client = OkHttpClient();
    val xmlDeserializer = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun getRadioPrograms(): List<ProgramModel> {

        var morePages = true
        val programs = arrayListOf<ProgramModel>();
        var apiUrl = "http://api.sr.se/api/v2/programs"



        while (morePages) {

            val response = sendRequest(apiUrl)
            val deserializedResponse = xmlDeserializer.readValue(response?.body?.string(), ProgramsResponse::class.java)

            println(deserializedResponse)
            for (program in deserializedResponse.programs.program) {
                programs.add(ProgramModel(program.id.toInt(), program.name, program.description))
            }

            if (deserializedResponse.pagination.nextPage !== null) {
                apiUrl = deserializedResponse.pagination.nextPage!!
            } else {
                morePages = false
            }
        }

        return programs

    }

    fun getRadioProgramEpisode(programId: Long): EpisodeModel? {
        val apiUrl = "http://api.sr.se/api/v2/episodes/getlatest?programid=$programId"

        val response = sendRequest(apiUrl)

        if (response != null) {

            val deserializedResponse = xmlDeserializer.readValue(response.body?.string(), EpisodesResponse::class.java)
            println(deserializedResponse)

            val lastEpisode = deserializedResponse.episode

            return EpisodeModel(
                programId.toInt(),
                lastEpisode.title,
                lastEpisode.description,
                lastEpisode.publishdateutc
            )
        }

        return null
    }


    private fun sendRequest(apiUrl: String): Response? {

        try {

            val request = Request.Builder()
                .url(apiUrl)
                .header("Accept", "application/xml")
                .build()

            val response = client.newCall(request).execute()

            // Response code
            val responseCode: Int = response.code

            if (responseCode == 200) {

                return response
            } else {

                throw IllegalStateException("Failed to get api information from Sveriges Radio. Response code $responseCode")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

}