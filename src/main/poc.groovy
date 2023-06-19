def xrayapi() {

    def responseToken = bat(returnStdout: true, script: '''
                    @set PAYLOAD={\\"client_id\\":\\"B15199C1802E4BAC9D0159818B1393D3\\",\\"client_secret\\":\\"8f535707a6e96bf4e7a304047bdace12d6dd2d4667a093ea213af93ea6a36994\\"}
                    @curl -X POST -H "Content-Type: application/json" ^
            -d "%PAYLOAD%" ^
            https://xray.cloud.getxray.app/api/v2/authenticate
    ''')

    def token = responseToken.trim().replaceAll('\"', '')
//  echo "BEARER TOKEN =>>>>>>>: ${token}"


    def responseofDelete = bat(returnStdout: true, script: """
            @set PAYLOAD={\\"query\\": \\"mutation { deleteTestExecution(issueId: \\\\\\"888420\\\\\\")}\\"}
            @curl -X POST -H "Authorization: Bearer ${token}" -H "Content-Type: application/json" -d "%PAYLOAD%" https://xray.cloud.getxray.app/api/v1/graphql
            """)

    echo "DELETE RESPONSE ${responseofDelete}"

}