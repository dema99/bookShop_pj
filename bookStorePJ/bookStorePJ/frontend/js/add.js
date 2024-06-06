const book_name = document.getElementById('book_name')
const author = document.getElementById('author')
const price = document.getElementById('price')


fetch('http://localhost:8080/api/category')
    .then(rsp => rsp.json())
    .then(categoryData => {
        categoryData.forEach(category => {
            const option = document.createElement('option')
            option.value = category.id
            option.text = category.name
            ctg.appendChild(option)
        })
        document.getElementById('save').addEventListener('click', () => {

            if (book_name.value == null || book_name.value === '') {
                alert('Ime knjige ne sme biti prazno')
                return
            }

            if (author.value == null || author.value === '') {
                alert('Ime autora ne sme biti prazno')
                return
            }

            if (price.value == null || price.value === '') {
                alert('Cena ne sme biti nula')
                return
            }

            fetch('http://localhost:8080/api/book', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    book_name: book_name.value,
                    author: author.value,
                    price: price.value,
                    categoryId: ctg.value
                })
            }).then(rsp => {
                if (rsp.ok) {
                    window.location.href = './index.html'
                    return
                }
                alert(`Dodavanje knige nije uspelo (HTTP ${rsp.status})`)
            })
        })
    })
