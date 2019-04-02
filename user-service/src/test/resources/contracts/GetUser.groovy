package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/users/1'
    }
    response {
        status 200
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
        body('''{"id":1,"sureName":"Turner","firstName":"Tom","gender":"male","email":"tom.turner@provider.de","subscribedNewsletter":true}''')
    }
}