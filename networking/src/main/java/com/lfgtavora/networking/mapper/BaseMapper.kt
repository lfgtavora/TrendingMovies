package com.lfgtavora.networking.mapper

interface BaseMapper {
    interface ToDomain<ENTITY, DOMAIN> {
       fun toDomain(entity: ENTITY): DOMAIN
    }
    interface ToEntity<DOMAIN, ENTITY> {
        fun toEntity(domain: DOMAIN): ENTITY
    }
}