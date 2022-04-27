import json
import pandas as pd
def predict():
    data = '[{"idBrand":0,"idCategory":0,"idProduct":2043,"search":"null"},{"idBrand":0,"idCategory":0,"idProduct":2045,"search":"null"},{"idBrand":0,"idCategory":0,"idProduct":2047,"search":"null"},{"idBrand":0,"idCategory":0,"idProduct":2044,"search":"null"},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"sam"},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"sam"},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"sam"},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"Điện thoại Samsung Galaxy A53 5G 128GB"},{"idBrand":0,"idCategory":1056,"idProduct":0,"search":"null"},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"Gậy chụp ảnh Xmobile Hình Pikachu CSA006 "},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"Gậy chụp ảnh Xmobile Hình Pikachu CSA006 "},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"sung"},{"idBrand":0,"idCategory":0,"idProduct":0,"search":"Điện thoại Samsung Galaxy A53 5G 128GB"}]'
    arr = json.loads(data)
    return arr

df = pd.DataFrame(predict())
print(df)
