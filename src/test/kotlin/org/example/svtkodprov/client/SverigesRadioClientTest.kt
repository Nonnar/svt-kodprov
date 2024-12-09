package org.example.svtkodprov.client

import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class SverigesRadioClientTest {

    private val client = SverigesRadioClient()

    @Test
    fun getAllPrograms() {

        val programs = client.getRadioPrograms()

        assert(programs.size == 784)

    }

    @Test
    fun getRadioProgramEpisode() {

        val episode = client.getRadioProgramEpisode(3718)

        assert(episode?.programId == 3718)
        assert(episode?.title == "Så undviker du algoritmerna")
        assert(episode?.description == "Fight, flight eller ge upp? Kjell, Elin och Olle firar stora algoritmbytardagen.")
        assert(episode?.published.toString() == "Wed Dec 04 23:07:00 CET 2024")

    }


}

