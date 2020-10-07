import org.w3c.dom.HTMLDivElement
import kotlin.browser.document

class BookStorePage(private val presenter: BookStoreContract.Presenter) : BookStoreContract.View {

    /*To access these DOM elements in your Kotlin code, <div>*/
    private val loader = document.getElementById("loader") as HTMLDivElement
    private val content = document.getElementById("content") as HTMLDivElement

    private val cardBuilder = CardBuilder()

    override fun showBooks(books: List<Book>) {
        books.forEach { book ->
            val card = cardBuilder.build(book)
            content.appendChild(card)
        }
    }

    override fun showLoader() {
        loader.style.visibility = "visible"
    }

    override fun hideLoader() {
        loader.style.visibility = "hidden"
    }

    fun show() {
        presenter.attach(this)
        presenter.loadBooks()
        /*This code sets the current BookStorePage instance as the presenter’s
        view so that it can receive callbacks from it,
        and then it asks the presenter to start loading the books.*/
    }

}

/*This class has a constructor with a BookStoreContract.Presenter parameter.
It implements the BookStoreContract.View interface with three required methods (empty, for now).*/