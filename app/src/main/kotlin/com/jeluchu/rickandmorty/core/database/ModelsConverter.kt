package com.jeluchu.rickandmorty.core.database

import androidx.room.TypeConverter
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import com.jeluchu.rickandmorty.features.characters.models.OriginEntity
import com.jeluchu.rickandmorty.features.characters.models.PageResultEntity
import com.jeluchu.rickandmorty.features.locations.models.LocationEntity
import kotlinx.serialization.json.Json

class ModelsConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun stringToLocationEntity(data: String?): List<LocationEntity>? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun locationEntityToString(someObjects: List<LocationEntity>?): String? = json.encodeToString(someObjects)

    @TypeConverter
    fun stringToPageResultEntity(data: String?): PageResultEntity? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun pageResultEntityToString(someObjects: PageResultEntity?): String? = json.encodeToString(someObjects)

    @TypeConverter
    fun stringToListCharacterEntity(data: String?): List<CharacterEntity>? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun listCharacterEntityToString(someObjects: List<CharacterEntity>?): String? = json.encodeToString(someObjects)

    @TypeConverter
    fun stringToCharacterEntity(data: String?): CharacterEntity? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun characterEntityToString(someObjects: CharacterEntity?): String? = json.encodeToString(someObjects)

    @TypeConverter
    fun stringToOriginEntity(data: String?): OriginEntity? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun originEntityToString(someObjects: OriginEntity?): String? = json.encodeToString(someObjects)

    @TypeConverter
    fun stringToLocationCharacterEntity(data: String?): com.jeluchu.rickandmorty.features.characters.models.LocationEntity? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun locationCharacterEntityToString(someObjects: com.jeluchu.rickandmorty.features.characters.models.LocationEntity?): String? = json.encodeToString(someObjects)

    @TypeConverter
    fun stringToStringList(data: String?): List<String>? = json.decodeFromString(data.orEmpty())

    @TypeConverter
    fun stringListToString(someObjects: List<String>?): String? = json.encodeToString(someObjects)
}
