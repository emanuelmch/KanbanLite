package bill.kanban.atomic

interface AtomicPresenter<Atom> {
    fun attach(view: AtomicView<Atom>)
    fun detach() {}
}

interface AtomicView<Atom> {
    fun render(atom: Atom)
}