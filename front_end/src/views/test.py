import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMasl_sbScaler
import numpy as np

# Load the data
data = pd.read_csv("data.csv")

# Separate features (SL and SB) and target (type)
sl_sb = data[['SL', 'SB']]
type = data['type']

# Normalize the input features to the range 0 to 1
scaler = MinMaxScaler()
sl_sb_normal = scaler.fit_transform(sl_sb)

# Create one-hot encoded target vectors
type_onehot = np.zeros((len(type), len(type.unique())))
type_onehot[np.arange(len(type)), type] = 1

# Split the data into training and testing sets (75% training, 25% testing)
sl_sb_train, sl_sb_test, type_train, type_test = train_test_split(sl_sb_normal, type_onehot, test_size=0.25, random_state=42)

# Print the shapes of the training and testing data
print("Shape of sl_sb_train:", sl_sb_train.shape)
print("Shape of sl_sb_test:", sl_sb_test.shape)
print("Shape of y_train:", type_train.shape)
print("Shape of y_test:", type_test.shape)

from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import SGD

# Create the Sequential model
model = Sequential()

# Add the first hidden layer with 128 neurons and ReLU activation function
model.add(Dense(128, input_dim=2, activation='relu'))

# Add the second hidden layer with 64 neurons and ReLU activation function
model.add(Dense(64, activation='relu'))

# Add the output layer with 4 neurons (one for each flower type) and softmax activation function
model.add(Dense(4, activation='softmax'))

# Define the optimizer with stochastic gradient descent, learning rate of 0.3, and momentum of 0.2
optimizer = SGD(lr=0.3, momentum=0.2)

# Compile the model with categorical crossentropy loss function and the defined optimizer
model.compile(loss='categorical_crossentropy', optimizer=optimizer, metrics=['accuracy'])

