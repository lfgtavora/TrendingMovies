# TrendingMovies

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


## :bug: Bugs em investigação

- [Crítico] Recomposition do Compose está afetando a viewmodel chamada da API tela de detalhes, causando um looping infinito. Problema foi reportado por diversos usuários na thread do Koin: https://github.com/InsertKoinIO/koin/issues/1079
