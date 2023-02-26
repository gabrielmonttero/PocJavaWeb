#autor: Gabriel M Pereira
#language: pt

@Executa
Funcionalidade: Pesquisa no Google
  Eu como usuário, vou realizar uma pesquisa no site do Google

  Contexto: Abrir o navegador
    Dado que eu acesse o site "https://www.google.com.br"

  @Executa
  Cenário: Pesquisa Java no Google
    Quando realizo pesquisa java
    Então valido a pesquisa e evidencio java
    E fecho o navegador

  @nExecuta
  Cenário: Pesquisa CSharp no Google
    Quando realizo pesquisa csharp
    Então valido a pesquisa e evidencio csharp
    E fecho o navegador