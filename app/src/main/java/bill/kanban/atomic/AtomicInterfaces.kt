package bill.kanban.atomic

abstract class AtomicPresenter<Atom : Any, in Action : Any?> {

    lateinit var currentState: Atom
        private set

    var view: AtomicView<Atom> = EmptyAtomicView()
        private set

    abstract val initialState: Atom

    fun attach(view: AtomicView<Atom>) {
        this.currentState = initialState
        this.view = view

        view.render(currentState)
        onViewAttached(view)
    }

    abstract fun onViewAttached(view: AtomicView<Atom>)

    open fun beforeViewDetached() {}

    fun detach() {
        beforeViewDetached()

        view = EmptyAtomicView()
    }

    fun emitAction(action: Action) {
        if (view is EmptyAtomicView) return

        currentState = reduce(currentState, action)
        view.render(currentState)
    }

    abstract fun reduce(atom: Atom, action: Action): Atom
}

interface AtomicView<in Atom> {
    fun render(atom: Atom)
}

class EmptyAtomicView<in Atom> : AtomicView<Atom> {
    override fun render(atom: Atom) {}
}
