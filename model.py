import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder
import tensorflow as tf
from tensorflow import keras
import numpy

def extract_data(wine_data_csv_name):
    data = pd.read_csv(wine_data_csv_name, sep=';')
    x = data.drop(['quality'], axis=1).copy()
    y = data['quality'].copy()
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=.20, random_state=42)
    x_train, x_val, y_train, y_val = train_test_split(x, y, test_size=.20, random_state=42)

    scaler = StandardScaler()
    encoder = LabelEncoder()
    x_train_scaled = scaler.fit_transform(x_train)
    x_test_scaled = scaler.transform(x_test)
    x_val_scaled = scaler.transform(x_val)

    y_train = encoder.fit_transform(y_train)
    y_test = encoder.transform(y_test)
    y_val = encoder.transform(y_val)

    return x_train_scaled, x_test_scaled, x_val_scaled, y_train, y_test, y_val


x_train, x_test,x_val, y_train, y_test, y_val = extract_data("winequality-white.csv")



model = keras.Sequential([
        keras.layers.Flatten(input_shape=(11,)),
        keras.layers.Dense(50, activation='relu'),
        keras.layers.Dense(100, activation='softmax'),
        keras.layers.Dense(50, activation='relu'),
        keras.layers.Dense(7)
])
model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.001, beta_1=0.9, beta_2=0.999),
            loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
            metrics=['accuracy']
    )
model.fit(x_train, y_train, epochs=132, validation_data=(x_val, y_val), batch_size=20)
model.evaluate(x_test, y_test)
