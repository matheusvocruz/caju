## L4

- Para garantir que apenas uma transação fosse processada por conta, poderia ser feito um controle de idempotência, com o DynamoDB para garantir a integridade da conta.
- Separar em alguns microsserviços para deixá-los menores e mais escaláveis.
- Processamento da transação, com sua criação e atualização de saldo, poderiam ser uma mensageira para não aguardar o processamento na resposta, pois a validação e o resto já foi feito.