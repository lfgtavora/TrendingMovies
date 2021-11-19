# TrendingMovies!

<img src="https://user-images.githubusercontent.com/44548041/142545228-686b080e-da93-4e48-9f9d-3147a316452f.png" width="300"> <img src="https://user-images.githubusercontent.com/44548041/142543167-6dbaff62-0576-4e12-9ef2-2a5c881d16d9.png" width="300"> <img src="https://user-images.githubusercontent.com/44548041/142543193-01f8f9bf-2295-4d2c-aec0-587588e24152.png" width="300">

## :memo: Descrição

Pequena aplicação de uma lista de filmes usando a API do https://www.themoviedb.org/. Nela usuários poderão sempre ficar atualizado dos melhores lançamentos de filmes do momento.

## :wrench: Principais tecnologias

### Koin
Service lcoator foi escolhido por questão de afinidade e por conta do koin isolation. 

### Jetpack Compose
Novo paradigma de construções de views android voltada a programação reativa.

### Ktor + kotlin serializer
Se mostrou uma ótima alternativa ao retrofit + gson, servindo de preparo para uma possivel implementação KMM (Kotlin Multiplatform).

### Coil
Uma opção sólida,leve e compativel com Compose cumprindo bem seu papel principal e servindo de alternativa para libraries mais conhecidas do mercado como Glide e Picasso

### FlowLayout
Ganha-se a opção de trabalhar como Rows e Colums com propriedades flexbox

## :building_construction: Arquitetura

A aplicação foi divida em seguintes módulos:
### Designsystem
Prove um design mais consistente e padronizado para modulos que precisem consumir views
### Networking
Separa a lógica de implementação do Ktor para ser reaproveitada por quaisquer modulos que necessitem fazer requisiçoes REST.
### Feed
Responsável por organizar as Views e carregar lógica de items consumidas na Home.
### Moviedetail
Responsável por organizar as Views e carregar lógica de items consumidas na página de detalhes de um filme.

## :construction: Melhorias futuras

- Separar funções composables em components mais especificos
- Migrar de Koin para Hilt.
- Adicionar paginação no feed principal com o Paging https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary.
- Enriquecer tela de detalhes de filmes com mais informações(como atores, trailer, estudios envolvidos).
- Filme randomico na Home.
- Escrever testes unitários com Mockk.
- Adicionar Placeholder (https://google.github.io/accompanist/placeholder/) para loadings mais semanticos.
