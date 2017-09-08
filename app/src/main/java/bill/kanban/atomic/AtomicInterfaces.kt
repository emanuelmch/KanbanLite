package bill.kanban.atomic

interface AtomicPresenter<Atom> {
    fun onViewReady()
}

interface AtomicView<Atom> {
    fun render(atom: Atom)
}