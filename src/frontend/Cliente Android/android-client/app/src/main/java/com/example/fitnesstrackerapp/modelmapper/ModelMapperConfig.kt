package com.example.fitnesstrackerapp.modelmapper

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies

object ModelMapperConfig {
    val modelMapper: ModelMapper = ModelMapper().apply {
        configuration.matchingStrategy = MatchingStrategies.STRICT
    }
}