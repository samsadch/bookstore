interface BookStoreContract {
    //The view will be able to:
    interface View {
        fun showBooks(books: List<Book>) // 1 Show a list of books provided to it.
        fun showLoader() // 2 Show a loading indicator while the app is fetching the book data from the server.
        fun hideLoader() // 3 Hide the loading indicator.
    }

    interface Presenter {
        fun attach(view: View) // 4 Display results on any view that it’s provided.
        fun loadBooks() // 5 Start loading the book data from the data source.
        // In this case, that’s a remote server.
    }
}