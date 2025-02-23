package camp.nextstep.edu.memo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.Event
import camp.nextstep.edu.memo.data.Injector
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val memoRepository: MemoRepository = Injector.providesMemoRepository()
) : ViewModel() {

    private val _memoList = MutableStateFlow<List<Memo>>(emptyList())
    val memoList: StateFlow<List<Memo>> get() = _memoList.asStateFlow()

    private val _memoEvent = MutableLiveData<Event<MemoEvent>>()
    val memoEvent: LiveData<Event<MemoEvent>> get() = _memoEvent

    fun fetchMemoList() {
        _memoList.value = memoRepository.fetch()
    }

    fun delete(uuid: UUID) {
        memoRepository.delete(uuid)
        _memoEvent.value = Event(MemoEvent.Delete(uuid))
    }
}
