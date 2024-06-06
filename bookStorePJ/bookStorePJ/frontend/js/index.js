const table = document.getElementById('table')
const template = document.getElementById('book')
const searchTitle = document.getElementById('search-title')

if (searchParam != null && searchParam !== '') {
    searchTitle.innerText = 'Pretraga knjiga po autoru'
    fetchBooks('/author/' + searchParam)
} else {
    searchTitle.innerText = 'Lista knjiga'
        fetchBooks()
}

function fetchBooks(url = '') {
    fetch(`http://localhost:8080/api/book${url}`)
        .then(rsp => rsp.json())
        .then(data => {
            if (data.length === 0) {
                alert('Knjiga nije pronadjena')
                fetchBooks()
                return
            }
            data.forEach(book => {
                const copy = template.content.cloneNode(true)
                copy.querySelector('.id').innerText = book.id
                copy.querySelector('.book_name').innerText = book.book_name
                copy.querySelector('.author').innerText = book.author
                copy.querySelector('.price').innerText = book.price
                copy.querySelector('.ctg').innerText = book.category.name
                copy.querySelector('.updated').innerText = formatDate(book.updatedAt)
                copy.querySelector('.edit').href = `./edit.html?id=${book.id}`
                copy.querySelector('.remove').addEventListener('click', ()=>{
                    if (confirm(`Zelite obrisati knjigu ${book.book_name} ${book.author}?`)){
                        fetch(`http://localhost:8080/api/book/${book.id}`,{
                            method: 'DELETE',
                        })
                            .then(rsp=> {
                                if (rsp.status === 204) {
                                    window.location.href = './index.html'
                                    return
                                }
                                alert(`Brisanje knjige nije uspelo (HTTP ${rsp.status})`)
                            })
                    }
                })
                table.appendChild(copy)
            })
        })

}
