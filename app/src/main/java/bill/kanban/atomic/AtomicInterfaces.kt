package bill.kanban.atomic

interface AtomicPresenter<Atom> {
    fun onViewReady()
    fun onViewDestroy() {}
}

interface AtomicView<Atom> {
    fun render(atom: Atom)
}