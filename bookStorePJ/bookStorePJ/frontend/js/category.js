const table = document.getElementById('category-table')
const template = document.getElementById('category')

fetch(`http://localhost:8080/api/category`)
    .then(rsp => rsp.json())
    .then(data => {
        data.forEach(category => {
            const copy = template.content.cloneNode(true)
            copy.querySelector('.id').innerText = category.id
            copy.querySelector('.name').innerText = category.name
            copy.querySelector('.updated').innerText = formatDate(category.updatedAt)
            copy.querySelector('.edit').href = `./editCategory.html?id=${category.id}`
            copy.querySelector('.remove').addEventListener('click', () => {
                if (confirm(`Zelite obrisati kategoriju ${category.name} ?`)) {
                    fetch(`http://localhost:8080/api/category/${category.id}`, {
                        method: 'DELETE',
                    })
                        .then(rsp => {
                            if (rsp.status === 204) {
                                window.location.href = './category.html'
                                return
                            }
                            alert(`Brisanje kategorije nije uspelo (HTTP ${rsp.status})`)
                        })
                }
            })
            table.appendChild(copy)
        })
    })
