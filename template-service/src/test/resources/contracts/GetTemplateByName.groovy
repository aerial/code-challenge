

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/templates/filter?name=TestName'
    }
    response {
        status 200
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
        body('''{"id":1,"name":"TestName","content":"TestContent"}''')
    }
}