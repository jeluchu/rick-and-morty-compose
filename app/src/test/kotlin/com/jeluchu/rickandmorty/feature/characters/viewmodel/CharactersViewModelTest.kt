package com.jeluchu.rickandmorty.feature.characters.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.network.Resource
import com.jeluchu.rickandmorty.features.characters.usecase.GetCharactersUseCase
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.characters.models.CharacterResult
import com.jeluchu.rickandmorty.features.characters.models.Location
import com.jeluchu.rickandmorty.features.characters.models.Origin
import com.jeluchu.rickandmorty.features.characters.models.PageResult
import com.jeluchu.rickandmorty.features.characters.viewmodel.CharactersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var viewModel: CharactersViewModel

    private val mockLocation = Location(
        name = "Earth (C-137)",
        url = "https://rickandmortyapi.com/api/location/1"
    )

    private val mockOrigin = Origin(
        name = "Earth (C-137)",
        url = "https://rickandmortyapi.com/api/location/1"
    )

    private val mockCharacter1 = Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = mockOrigin,
        location = mockLocation,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/1"),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z"
    )

    private val mockCharacter2 = Character(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = mockOrigin,
        location = mockLocation,
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        episode = listOf("https://rickandmortyapi.com/api/episode/1"),
        url = "https://rickandmortyapi.com/api/character/2",
        created = "2017-11-04T18:50:21.651Z"
    )

    private val mockPageResultWithNext = PageResult(
        count = 826,
        pages = 42,
        next = "https://rickandmortyapi.com/api/character?page=2",
        prev = ""
    )

    private val mockPageResultWithoutNext = PageResult(
        count = 826,
        pages = 42,
        next = "",
        prev = "https://rickandmortyapi.com/api/character?page=1"
    )

    private val mockSuccessResult = CharacterResult(
        currentPage = 1,
        info = mockPageResultWithNext,
        results = listOf(mockCharacter1, mockCharacter2)
    )

    private val mockSuccessResultPage2 = CharacterResult(
        currentPage = 2,
        info = mockPageResultWithoutNext,
        results = listOf(mockCharacter2)
    )

    private val mockFailure = Failure.NetworkConnection(errorMessage = "Network error")

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(): CharactersViewModel {
        return CharactersViewModel(getCharactersUseCase)
    }

    @Test
    fun `init should load first page of characters successfully`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResult)))

        // When
        viewModel = createViewModel()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(1, state.currentPage)
        assertFalse(state.isLoading)
        assertEquals(2, state.characters.size)
        assertEquals("Rick Sanchez", state.characters[0].name)
        assertEquals("Morty Smith", state.characters[1].name)
        assertTrue(state.hasMorePages)
        assertEquals(null, state.error)
    }

    @Test
    fun `init should handle loading state correctly`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Loading()))

        viewModel = createViewModel()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.isLoading)
        assertTrue(state.characters.isEmpty())
    }

    @Test
    fun `init should handle error state correctly`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Error(mockFailure)))

        viewModel = createViewModel()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertTrue(state.characters.isEmpty())
        assertEquals("Network Connection Failed: Network error", state.error)
    }

    @Test
    fun `loadCharacters should reset state and load first page`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResult)))

        viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.loadCharacters()
        advanceUntilIdle()

        verify(getCharactersUseCase, times(2)).invoke(GetCharactersUseCase.Params(1))
        val state = viewModel.state.value
        assertEquals(1, state.currentPage)
        assertEquals(2, state.characters.size)
    }

    @Test
    fun `loadMoreCharacters should append characters to existing list`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResult)))

        viewModel = createViewModel()
        advanceUntilIdle()

        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(2)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResultPage2)))

        viewModel.loadMoreCharacters()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(2, state.currentPage)
        assertEquals(3, state.characters.size)
        assertTrue(state.hasMorePages)
        assertFalse(state.isLoading)
        assertFalse(state.isLoadingMore)
    }

    @Test
    fun `loadMoreCharacters should handle loading state correctly`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResult)))

        viewModel = createViewModel()
        advanceUntilIdle()

        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(2)))
            .thenReturn(flowOf(Resource.Loading()))

        viewModel.loadMoreCharacters()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.isLoading)
        assertEquals(2, state.characters.size) // Should keep existing characters
    }

    @Test
    fun `loadMoreCharacters should handle error correctly`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResult)))

        viewModel = createViewModel()
        advanceUntilIdle()

        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(2)))
            .thenReturn(flowOf(Resource.Error(mockFailure)))

        viewModel.loadMoreCharacters()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("Network Connection Failed: Network error", state.error)
        assertEquals(2, state.characters.size) // Should keep existing characters
        assertEquals(1, state.currentPage) // Should not update currentPage on error
    }

    @Test
    fun `loadMoreCharacters should increment page correctly`() = runTest {
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResult)))

        viewModel = createViewModel()
        advanceUntilIdle()

        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(2)))
            .thenReturn(flowOf(Resource.Success(mockSuccessResultPage2)))

        viewModel.loadMoreCharacters()
        advanceUntilIdle()

        verify(getCharactersUseCase).invoke(GetCharactersUseCase.Params(2))
        assertEquals(2, viewModel.state.value.currentPage)
    }

    @Test
    fun `should handle empty results correctly`() = runTest {
        val emptyResult = CharacterResult(
            currentPage = 1,
            info = mockPageResultWithoutNext,
            results = emptyList()
        )
        whenever(getCharactersUseCase.invoke(GetCharactersUseCase.Params(1)))
            .thenReturn(flowOf(Resource.Success(emptyResult)))

        viewModel = createViewModel()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.characters.isEmpty())
        assertTrue(state.hasMorePages)
        assertFalse(state.isLoading)
    }
}