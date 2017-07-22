1) os métodos set são desnecessários e/ou até perigosos como é o caso do setId.
   Nessas classes, você poderia trabalhar com atributos imutáveis e apenas construtores e métodos getters

2) não é necessário repetir código nos construtores.
   Os que recebem menos parâmetros poderiam utilizar em seu código "this(param1, param2, null, null) por exemplo.

3) acredito que seja desnecessário ter a lista de funcionalidades dentro da classe Plugin.
   Isso deixa ela mais "pesada".
   Ex.: poderia ter uma interface que tenha apenas uma lista dos plugins.
   Nesse caso não é necessário carregar do banco as informações das funcionalidades.
   De qualquer forma, essa lista também poderia ser inicializada pelo construtor e o método get, deveria retornar uma lista não modificável para garantir que nenhum objeto seja removido ou adicionado.
   OBS> Alterar atributos para final e aceitar apenas na criação

4) semelhante ao 3, não é necessário ter o Plugin carregado dentro da classe Functionality, bastaria o identificador.
