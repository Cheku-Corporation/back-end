import requests
import base64

# ** Function to encode a string using Base64 encoding **
def encode(string):
  # Encode the string using utf-8 encoding, then decode the resulting bytes using Base64 encoding
  return base64.b64encode(string.encode('utf-8')).decode('utf-8')

# ** Function to decode a string using Base64 decoding **
def decode(string):
  # Encode the string using utf-8 encoding, then decode the resulting bytes using Base64 decoding
  return base64.b64decode(string.encode('utf-8')).decode('utf-8')

# ** Function to send a POST request to the specified URL with the specified data, JSON, and headers ** 
def post(url, data=None, json=None, headers=None):
    # Send the POST request using the requests library
    r = requests.post(url, data=data, json=json, headers=headers)
    print(r.text)
    return r

# ** Function to send a GET request to the specified URL with the specified headers**
def get(url, headers=None):
    # Send the GET request using the requests library
    r = requests.get(url, headers=headers)
    print(r.text)
    return r
    
# ** Function to register users with the specified URL **
def register(url):
    # Send a POST request to the specified URL to register an admin user
    post(url, json={'email': "admin@gamil.com",'password': "admin","passwordConfirmation": "admin","name": "admin","role":"ADMIN","groupName": "","groupId": ""})
    
    # Register additional users with different roles and group associations
    post(url, json={'email': "joao@gmail.com",'password': "joao","passwordConfirmation": "joao","name": "Joao Dias","role":"USER","groupName": "FamiliaDias","groupId": ""})
    post(url, json={'email': "matilde@gmail.com",'password': "matilde","passwordConfirmation": "matilde","name": "Matilde Dias","role":"USER","groupName": "","groupId": encode("FamiliaDias")})
    post(url, json={'email': "dario@gmail.com",'password': "dario","passwordConfirmation": "dario","name": "Dario SIlva","role":"USER","groupName": "MyCollection","groupId": ""})
    post(url, json={'email': "cheku@gmail.com",'password': "cheku","passwordConfirmation": "cheku","name": "Julio Cesar","role":"USER","groupName": "Checku","groupId": ""})
    post(url, json={'email': "julia@gmail.com",'password': "julia","passwordConfirmation": "julia","name": "julia Monteiro","role":"USER","groupName": "","groupId": encode("Checku")})
    post(url, json={'email': "martin@gmail.com",'password': "martin","passwordConfirmation": "martin","name": "Martin Gomes","role":"USER","groupName": "","groupId": encode("Checku")})
    post(url, json={'email': "david@gmail.com",'password': "david","passwordConfirmation": "david","name": "David Manarte","role":"USER","groupName": "","groupId": encode("FamiliaDias")})
    post(url, json={'email': "joana@gmail.com",'password': "joana","passwordConfirmation": "joana","name": "Joana Dias","role":"USER","groupName": "MyCars","groupId": ""})
    post(url, json={'email': "bruno@gmail.com",'password': "bruno","passwordConfirmation": "bruno","name": "Bruno Martins","role":"USER","groupName": "","groupId": encode("Checku")})
    post(url, json={'email': "Joca@gmail.com",'password': "joca","passwordConfirmation": "joca","name": "Joca Laranjeira","role":"USER","groupName": "CarJoca","groupId": ""})


# **Function to Login user with the specified URL **
# Return the 'Authorization' field from the JSON response
def login(url):
    return post(url, json={'email': "admin@gamil.com",'password': "admin"}).json()['Authorization']
    
    # Uncomment the following line to log in a user with the email "joao@gmail.com" and password "joao"
    # return post(url, json={'email': "joao@gmail.com", 'password': "joao"}).json()['Authorization']

# ** Function to create tires with the specified URL **
def tires(url,token):
    # Michelin
    post(url, headers={'Authorization': token}, json = {"brand":"Michelin", "model":"Primacy 4+"})
    post(url, headers={'Authorization': token}, json = {"brand":"Michelin", "model":"CrossClimate"})
    post(url, headers={'Authorization': token}, json = {"brand":"Michelin", "model":"Pilot Sport 4"})
    post(url, headers={'Authorization': token}, json = {"brand":"Michelin", "model":"Pilot Sport Cup 2"})
    post(url, headers={'Authorization': token}, json = {"brand":"Michelin", "model":"Pilot Alpin 5"})
    post(url, headers={'Authorization': token}, json = {"brand":"Michelin", "model":"Pilot Alpin PA4"})
    # Pirelli
    post(url, headers={'Authorization': token}, json = {"brand":"Pirelli", "model":"Cinturato All Season"})
    post(url, headers={'Authorization': token}, json = {"brand":"Pirelli", "model":"Scorpion Verde All Season"})
    post(url, headers={'Authorization': token}, json = {"brand":"Pirelli", "model":"Winter Sottozero 3"})
    post(url, headers={'Authorization': token}, json = {"brand":"Pirelli", "model":"Scorpion winter"})
    post(url, headers={'Authorization': token}, json = {"brand":"Pirelli", "model":"Carrier winter"})
    # Continental
    post(url, headers={'Authorization': token}, json = {"brand":"Continental", "model":"SportContact 6"})
    post(url, headers={'Authorization': token}, json = {"brand":"Continental", "model":"ContiCrossContact LX Sport"})
    post(url, headers={'Authorization': token}, json = {"brand":"Continental", "model":"PremiumContact 6"})
    # Goodyear
    post(url, headers={'Authorization': token}, json = {"brand":"Goodyear", "model":"EfficientGrip Performance"})
    post(url, headers={'Authorization': token}, json = {"brand":"Goodyear", "model":"Eagle F1 SuperSport"})
    post(url, headers={'Authorization': token}, json = {"brand":"Goodyear", "model":"Vector 4Seasons Gen-2"})
    # Dunlop
    post(url, headers={'Authorization': token}, json = {"brand":"Dunlop", "model":"Sport Maxx RT"})
    post(url, headers={'Authorization': token}, json = {"brand":"Dunlop", "model":"Sport BluResponse"})
    post(url, headers={'Authorization': token}, json = {"brand":"Dunlop", "model":"StreetResponse"})
    post(url, headers={'Authorization': token}, json = {"brand":"Dunlop", "model":"Grandtrek Touring A/S"})
    # Bridgestone
    post(url, headers={'Authorization': token}, json = {"brand":"Bridgestone", "model":"Potenza S001"})
    post(url, headers={'Authorization': token}, json = {"brand":"Bridgestone", "model":"Weather Control A005"})
    post(url, headers={'Authorization': token}, json = {"brand":"Bridgestone", "model":"Turanza ER300"})


# ** Function to create motores with the specified URL **
def motors(url,token):
    post(url,headers={'Authorization': token}, json ={"model": "116i 1.5 MT","displacement": 1499 , "power":109} )
    post(url,headers={'Authorization': token}, json ={"model": "1.6 AMT","displacement": 1595 , "power":156} )
    post(url,headers={'Authorization': token}, json ={"model": "2.4i 2WD","displacement": 2351 , "power":126} )
    post(url,headers={'Authorization': token}, json ={"model": "0.9 AT","displacement": 875 , "power":85} )
    post(url,headers={'Authorization': token}, json ={"model": "2.4 i 16V","displacement": 2360 , "power":174} )
    post(url,headers={'Authorization': token}, json ={"model": "1.8 i 16V","displacement": 1795 , "power":122} )
    post(url,headers={'Authorization': token}, json ={"model": "1.8 i 16V","displacement": 1798 , "power":125} )
    post(url,headers={'Authorization': token}, json ={"model": "4.3 i V6","displacement": 4293 , "power":163} )
    post(url,headers={'Authorization': token}, json ={"model": "1.3 i 16V","displacement": 1299 , "power":86} )
    post(url,headers={'Authorization': token}, json ={"model": "1.6 i 16V","displacement": 1598 , "power":115} )

TypeCombustivel = ["GASOLINE", "DIESEL", "HYBRID", "ELECTRIC"]

# ** Function to create model the cars with the specified URL **
def car_model(url,token):
    post(url,headers={'Authorization': token}, json ={"brand":"BMW","model":"1er II (F20-F21) Facelift Hatchback","year":2015,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":1},"tires":{"id":1}})
    post(url,headers={'Authorization': token}, json ={"brand":"Mercedes-Benz","model":"CLA-klasse I (C117) Facelift Wagon Shooting brake","year":2016,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":2},"tires":{"id":9}})
    post(url,headers={'Authorization': token}, json ={"brand":"Land Rover","model":"Landscape","year":2015,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":3},"tires":{"id":2}})
    post(url,headers={'Authorization': token}, json ={"brand":"Fiat","model":"500 II Hatchback Facelift","year":2015,"type":"DIESEL","tankCapacity": 50,"motor":{"id":4},"tires":{"id":3}})
    post(url,headers={'Authorization': token}, json ={"brand":"Jeep","model":"Patriot","year":2010,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":5},"tires":{"id":4}})
    post(url,headers={'Authorization': token}, json ={"brand":"Lotus","model":"Elise II","year":2016,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":6},"tires":{"id":5}})
    post(url,headers={'Authorization': token}, json ={"brand":"Mazda","model":"MX-5 Roadster","year":2002,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":7},"tires":{"id":5}})
    post(url,headers={'Authorization': token}, json ={"brand":"Chevrolet","model":"Astro","year":2015,"type":"GASOLINE","tankCapacity": 50,"motor":{"id":8},"tires":{"id":8}})
    post(url,headers={'Authorization': token}, json ={"brand":"Toyota","model":"Yaris Verso","year":2005,"type":"DIESEL","tankCapacity": 50,"motor":{"id":9},"tires":{"id":10}})
    post(url,headers={'Authorization': token}, json ={"brand":"Volkswagen","model":"Golf VI Variant","year":2010,"type":"HYBRID","tankCapacity": 50,"motor":{"id":10},"tires":{"id":10}})

# ** Function to register car with the specified URL **
def car(url,token):
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"1er II (F20-F21) Facelift Hatchback"},"matricula": "AA-11-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":1},"userId":2})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model": "CLA-klasse I (C117) Facelift Wagon Shooting brake"},"matricula": "AA-22-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":2},"userId":4})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"Landscape"},"matricula": "AA-33-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":3},"userId":5})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"500 II Hatchback Facelift"},"matricula": "AA-44-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":4},"userId":9})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"Elise II"},"matricula": "AA-55-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":5},"userId":11})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"Patriot"},"matricula": "AA-66-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":4},"userId":9})    
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"MX-5 Roadster"},"matricula": "AA-77-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":1},"userId":2})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"Astro"},"matricula": "AA-88-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":3},"userId":5})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"Yaris Verso"},"matricula": "AA-99-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":3},"userId":5})
    post(url,headers={'Authorization': token}, json ={"carModel": {"model":"Golf VI Variant"},"matricula": "AA-00-XX","inspectionDate": 132456789,"insuranceDate": 123456789,"group":{"id":5},"userId":11})

def main():
    # **Post**
    register('http://localhost:8080/api/register')
    token = login("http://localhost:8080/login")
    tires("http://localhost:8080/admin/tires", token)
    motors("http://localhost:8080/admin/motor", token)
    car_model("http://localhost:8080/admin/carModel", token)
    car("http://localhost:8080/api/car", token)


if __name__ == '__main__':
    main()
    


