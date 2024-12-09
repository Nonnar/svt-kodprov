package org.example.svtkodprov.controller

import org.example.svtkodprov.entity.RadioProgram
import org.example.svtkodprov.model.EpisodeModel
import org.example.svtkodprov.service.RadioProgramService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api"])
class RadioProgramController(private val radioProgramService: RadioProgramService) {

    /**
     * Return latest episode for a given program name
     */
    @GetMapping("/getLatestEpisode/{programName}")
    fun getRadioProgramLatestEpisode(@PathVariable("programName") programName: String): EpisodeModel? =
        radioProgramService.getRadioProgramLatestEpisode(programName)

    /**
     * Gets number of programs in database
     */
    @GetMapping("/getPrograms")
    fun getPrograms(): Int = radioProgramService.getRadioPrograms()

    /**
     * Updates database with programs from sveriges radio and returns newly added ones
     */
    @GetMapping("updatePrograms")
    fun updatePrograms(): List<RadioProgram> = radioProgramService.updateAllRadioPrograms()

}

