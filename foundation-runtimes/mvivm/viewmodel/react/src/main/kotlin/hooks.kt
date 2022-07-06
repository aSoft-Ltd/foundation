import viewmodel.ViewModel

@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
fun <S> useViewModelState(vm: ViewModel<S>): S = useLive(vm.ui)
