from bs4 import BeautifulSoup
import requests
import re
import os

def get_pokes(pagina):
    r = requests.get(pagina)
    data = r.text
    # Creamos el objeto soup y le pasamos lo capturado con request
    soup = BeautifulSoup(data, 'lxml')    
    titulo = soup.title.text
    
    print(titulo)
    #filtrado de img y gifs
    todas = soup.find_all('img',src=re.compile('/([a-zA-Z]*.png)/'))
    out = [x['src'] for x in todas]
    print(out)       
    return out


def save(pokelist):
    for x in pokelist:
        os.popen('wget \''+x+'\' -O '+get_name(x)+' -o log')
        
def get_name(url):
    chop = re.search('/([a-zA-Z]*.png)/', url)
    if chop:
        return chop.group(1)
    return None

def gen_inserts():
    out = ""
    a = os.popen('ls *.png').read()
    l = a.split()
    for x in l:
        out += "INSERT INTO pokemon(name, url) VALUES(\""+x[:-4]+"\",\"static/"+x+"\");\n"
    return out
    
c = get_pokes("http://nintendo.wikia.com/wiki/Category:First_generation_Pok%C3%A9mon_images")
save(c)
#print(gen_inserts())
