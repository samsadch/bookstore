import org.w3c.xhr.XMLHttpRequest

// 1 Implement the BookStoreContract.Presenter interface.
class BookStorePresenter : BookStoreContract.Presenter {

    // 2Add a lateinit property to keep a reference to the view.
    private lateinit var view: BookStoreContract.View

    // 3Implement the attach() method from the BookStoreContract.
    // Presenter interface, and initialize the view property with the received parameter
    override fun attach(view: BookStoreContract.View) {
        this.view = view
    }


    // 4 implement the loadBooks() method required by the
    // BookStoreContract.Presenter interface
    override fun loadBooks() {
        //Ask the view to show a loading indicator before you start loading the data.
        view.showLoader()


        //Make the asynchronous request to get the books’ data.
        getAsync(API_URL) { response ->
            //3 Parse the JSON response received as an array of instances of the Book data clas
            val books = JSON.parse<Array<Book>>(response)

            /*books.forEach { book ->
                println(book.title)
            }*/
            //4 Ask the view to hide the loading indicator, since you’ve finished loading and parsing.
            view.hideLoader()


            //5 sk the view to show the list of books.
            view.showBooks(books.toList())
        }
    }


    //Create a new method that makes a network request.
    //It takes a URL to fetch from, as well as a function with a String parameter,
    //which it will pass the result of the network call to.
    private fun getAsync(url: String, callback: (String) -> Unit) {

        val xmlHttp = XMLHttpRequest() //Create a new XMLHttpRequest instance


        xmlHttp.open("GET", url) //Set this request up so that it sends an HTTP GET to the given URL.

        // 4 Set a callback which will be invoked when the request completes.
        xmlHttp.onload = {
            // 5Check if the request is in a done (4) state, and if it has an OK (200) status code.
            if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
                // 6Call the callback function received as a parameter,
                // and pass it the contents of the network response as a single string.
                callback.invoke(xmlHttp.responseText)
            }
        }
        // 7 Invoke send() to fire off the HTTP request you’ve set up
        xmlHttp.send()
    }
}