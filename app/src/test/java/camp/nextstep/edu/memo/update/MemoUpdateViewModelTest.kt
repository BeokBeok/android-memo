package camp.nextstep.edu.memo.update

import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MemoUpdateViewModelTest {

    private val memoRepository: MemoRepository = mockk(relaxed = true)
    private lateinit var viewModel: MemoUpdateViewModel

    @BeforeEach
    fun setup() {
        viewModel = MemoUpdateViewModel(memoRepository = memoRepository)
    }

    @Test
    fun `특정 메모를 수정합니다`() {
        val memo = Memo(uuid = ID, value = "메모")
        viewModel.update(uuid = memo.uuid, memo = memo.value)

        verify {
            memoRepository.update(memo = memo)
        }
        assertEquals(viewModel.memoEvent.value, MemoEvent.Update)
    }

    companion object {
        private val ID = UUID.randomUUID()
    }
}
