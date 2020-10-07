val API_URL = js("getApiUrl()") as String
fun main(args: Array<String>) {
    val bookPresenter = BookStorePresenter() //create a new instance of BookStorePresenter
    val bookStorePage = BookStorePage(bookPresenter) //instance of BookStorePage
    //passing the page the presenter instance via its constructor.
    bookPresenter.attach(bookStorePage) //attach the page to the presenter
    //bookPresenter.loadBooks()
    //bookStorePage.hideLoader()
    bookStorePage.show()
}