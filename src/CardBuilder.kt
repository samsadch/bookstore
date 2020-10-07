import org.w3c.dom.*
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass

class CardBuilder {
    fun build(book: Book): HTMLElement {
        //1Create new elements by using the createElement() browser API,
        // passing in the name of the HTML tag to create. For example,
        // use "div" to create an HTMLDivElement and "img" to create an HTMLImageElement.
        val containerElement = document.createElement("div") as HTMLDivElement
        val imageElement = document.createElement("img") as HTMLImageElement
        val titleElement = document.createElement("div") as HTMLDivElement
        val priceElement = document.createElement("div") as HTMLDivElement
        val descriptionElement = document.createElement("div") as HTMLDivElement
        val viewDetailsButtonElement = document.createElement("button") as HTMLButtonElement

        // 2 Bind the book data to the HTML elements you created.
        bind(book = book,
                imageElement = imageElement,
                titleElement = titleElement,
                priceElement = priceElement,
                descriptionElement = descriptionElement,
                viewDetailsButtonElement = viewDetailsButtonElement)

        // 3 Apply some CSS classes to the HTML elements you created.
        applyStyle(containerElement,
                imageElement = imageElement,
                titleElement = titleElement,
                priceElement = priceElement,
                descriptionElement = descriptionElement,
                viewDetailsButtonElement = viewDetailsButtonElement)

        // 4 Append all of the individual HTML elements to one container.
        containerElement
                .appendChild(
                        imageElement,
                        titleElement,
                        descriptionElement,
                        priceElement,
                        viewDetailsButtonElement
                )
        // 5 Return the container, which is the root element of the card.
        return containerElement

    }

    // 6 Write an extension function that enables you to append a
    // variable number of children to an element,
    // instead of having to call the regular appendChild() method many times.
    private fun Element.appendChild(vararg elements: Element) {
        elements.forEach {
            this.appendChild(it)
        }
    }




    private fun bind(book: Book,
                     imageElement: HTMLImageElement,
                     titleElement: HTMLDivElement,
                     priceElement: HTMLDivElement,
                     descriptionElement: HTMLDivElement,
                     viewDetailsButtonElement: HTMLButtonElement) {

        // 1  Set the book cover image URL as the source of the image element on the card.
        imageElement.src = book.coverUrl

        // 2 Set the text content for the various text elements.
        titleElement.innerHTML = book.title
        priceElement.innerHTML = book.price
        descriptionElement.innerHTML = book.description
        viewDetailsButtonElement.innerHTML = "view details"

        // 3 Add a click event listener to the button element,
        // which will navigate to the book’s URL if the button is clicked.
        viewDetailsButtonElement.addEventListener("click", {
            window.open(book.url)
        })
    }



    private fun applyStyle(containerElement: HTMLDivElement,
                           imageElement: HTMLImageElement,
                           titleElement: HTMLDivElement,
                           priceElement: HTMLDivElement,
                           descriptionElement: HTMLDivElement,
                           viewDetailsButtonElement: HTMLButtonElement) {
        containerElement.addClass("card", "card-shadow")
        imageElement.addClass("cover-image")
        titleElement.addClass("text-title", "float-left")
        descriptionElement.addClass("text-description", "float-left")
        priceElement.addClass("text-price", "float-left")
        viewDetailsButtonElement.addClass("view-details", "ripple", "float-right")
    }
}