package org.example.svtkodprov.service

import org.example.svtkodprov.client.SverigesRadioClient
import org.example.svtkodprov.entity.RadioProgram
import org.example.svtkodprov.model.EpisodeModel
import org.example.svtkodprov.model.ProgramModel
import org.example.svtkodprov.repository.RadioProgramRepository
import org.springframework.stereotype.Service

@Service
class RadioProgramService(
    private val radioProgramRepository: RadioProgramRepository,
) {

    val sverigesRadioClient = SverigesRadioClient()

    fun getRadioPrograms(): Int = radioProgramRepository.findAll().count()

    fun getAllProgramsFromSverigesRadio(): List<ProgramModel> = sverigesRadioClient.getRadioPrograms()

    fun updateAllRadioPrograms(): List<RadioProgram> {
        val srPrograms = getAllProgramsFromSverigesRadio()
        val programs = ArrayList<RadioProgram>()

        for (program in srPrograms) {

            if (radioProgramRepository.findAllByProgramId(program.id.toLong()).isEmpty()) {
                programs.add(
                    radioProgramRepository.save(
                        RadioProgram(
                            0,
                            program.id.toLong(),
                            program.name,
                            program.description
                        )
                    )
                )

            }

        }

        return programs
    }

    fun getRadioProgramLatestEpisode(name: String): EpisodeModel? {
        val radioPrograms = radioProgramRepository.findAllByName(name)
        val radioProgramCount = radioPrograms.count()

        if (radioProgramCount == 0) {
            throw IllegalArgumentException("No program found with name $name")
        } else if (radioProgramCount == 1) {
            val episodeModel = sverigesRadioClient.getRadioProgramEpisode(radioPrograms[0].programId)

            return episodeModel
        } else {
            throw IllegalArgumentException("Multiple programs found with name $name")
        }
    }

}






