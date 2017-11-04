secure_scheme_headers={'X-FORWARDED-PROTOCOL': 'ssl', 'X-FORWARDED-PROTO': 'https', 'X-FORWARDED-SSL': 'on'}
proxy_protocol = True
accept  = 443
connect = 80
cert = "~/fullchain.pem"
key = "~/privkey.pem"
