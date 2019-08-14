# cadastro-clientes
Resolução do desafio: https://github.com/gustavodallanora/spring-boot-interview

Foram usando no projeto: SpringBoot2, Banco H2 para persistencia(com dados para teste ja inseridos ao iniciar o sistema) e Maven.
# Utilizando a api

**Cadastrar cidade**
  -"/cidades" Metodo POST (Cidade deve ser enviada vai RequestBody e são esperado o "nome" e um ObjetoEstado("estado":{"id":x}))
  
**Cadastrar cliente**
  -"/clientes" Metodo POST (Cliente deve ser enviado via RequestBody e são esperados "nomeCompleto","sexo"(sendo interos: 1=HOMEM,2=MULHER,3=OUTRO),"dataNascimento"(dd/MM/yyyy) e ObjetoCidade("cidade:"{"id":x})
  
**Consultar cidade pelo nome**
  -"/cidades/{nomeDaCidade}" Metodo GET (Retorna uma lista de Cidades)
  
**Consultar cidade pelo estado**
  -"/cidades/estado/{nomeDoEstado}" Metodo GET (Retornar uma lista de Cidades)
  
**Consultar todos clientes**
  -"/clientes" Metodo GET
  
 **Consultar cliente pelo nome**
  -"/clientes/nome" Metodo GET (O nome deve ser enviado via RequestBody, sendo passado como atribulo "nomeCompleto")
  
**Consultar cliente pelo Id**
  -"/clientes/id/{id}" Metodo GET
  
**Remover cliente**
  -"/clientes/{id}" Metodo DELETE

**Alterar o nome do cliente**
  -"/clientes" Metodo PUT (A alteração é feita via RequestBody, sendo passado como atribulo o ID do clientes e o "nomeCompleto" a ser alterado)
