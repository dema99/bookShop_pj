const id = params.get('id');

if (id == null || id === '')
    window.location.href = 'index.html'

const breadcrumb = document.getElementById('breadcrumb');
const bid = document.getElementById('id')
const book_name = document.getElementById('book_name')
const author = document.getElementById('author')
const price = document.getElementById('price')
const ctg= document.getElementById('ctg')
const updated = document.getElementById('updated')



fetch('http://localhost:8080/api/book/' + id)
    .then(rsp => {
        if (rsp.status === 200)
            return rsp.json()

        alert('Knjiga nije pronadjena')
        window.location.href = './index.html'
    })
    .then(data => {
        breadcrumb.innerText = `${data.book_name} | ${data.author}`
        bid.value = data.id
        book_name.value = data.book_name
        author.value = data.author
        price.value = data.price

        fetch('http://localhost:8080/api/category')
            .then(rsp => rsp.json())
            .then(categoryData => {
                categoryData.forEach(category => {
                    const option = document.createElement('option')
                    if (category.id === data.category.id){
                        option.selected = true
                    }
                    option.value = category.id
                    option.text = category.name
                    ctg.appendChild(option)
                })
            })

        updated.value = formatDate(data.updatedAt)

        document.getElementById('save').addEventListener('click', () => {
            fetch(`http://localhost:8080/api/book/${data.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    book_name: book_name.value,
                    author: author.value,
                    price: price.value,
                    categoryId: ctg.value

                })
            })
                .then(rsp => {
                    if (rsp.ok) {
                        window.location.href = './index.html'
                        return
                    }
                    alert(`Izmena knjige nije uspelo (HTTP ${rsp.status})`)
                })
        })
    })