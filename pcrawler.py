from urllib.request import urlopen
from bs4 import BeautifulSoup

f = open('kr.txt', 'a')

def write(before):
    if before is not None:        
        before = before.replace("-","")
        before = before.replace("^","")
        after = before.replace(":","")
        f.write(after+'\n')

for i in range(1, 10000):
    page = urlopen("https://stdict.korean.go.kr/api/view.do?key=A9CAF26B128DB44DA671FA8334A73CE9&method=TARGET_CODE&q="+str(i)) #key값은 따로 변경하세요
    document = page.read()
    page.close()

    soup = BeautifulSoup(document, 'html.parser')

    questions_list = soup.find_all("word")
    questions_list2 = soup.find_all("conjugation")
    questions_list3 = soup.find_all("abbreviation")

    for question in questions_list:
        write(question.get_text())
        
    for question in questions_list2:
        write(question.get_text())
        
    for question in questions_list3:
        write(question.get_text())

f.close()