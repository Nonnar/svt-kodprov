package org.example.svtkodprov.controller

import org.example.svtkodprov.entity.RadioProgram
import org.example.svtkodprov.model.EpisodeModel
import org.example.svtkodprov.service.RadioProgramService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun getRadioProgramLatestEpisode(@PathVariable("programName") programName: String): ResponseEntity<EpisodeModel?> =
        ResponseEntity<EpisodeModel?>(radioProgramService.getRadioProgramLatestEpisode(programName), HttpStatus.OK)

    /**
     * Gets number of programs in database
     */
    @GetMapping("/getPrograms")
    fun getPrograms(): ResponseEntity<Int> = ResponseEntity<Int>(radioProgramService.getRadioPrograms(), HttpStatus.OK)

    /**
     * Updates database with programs from Sveriges Radio and returns newly added ones
     */
    @GetMapping("updatePrograms")
    fun updatePrograms(): ResponseEntity<List<RadioProgram>> {
        val programs = radioProgramService.updateAllRadioPrograms()
        if (programs.isNotEmpty()) {
            return ResponseEntity<List<RadioProgram>>(programs, HttpStatus.CREATED)
        }
        return ResponseEntity<List<RadioProgram>>(programs, HttpStatus.OK)
    }

}

