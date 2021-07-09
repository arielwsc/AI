import sys

trainFile = open(sys.argv[1])
testFile = open(sys.argv[2])
classifier1 = []
classifier2 = []

#For train file:
trainAttr = []
line = trainFile.readline()

for word in line.split():
     trainAttr.append(word)
    
trainInstances = []
singleInstance = []

for line in trainFile:
    for word in line.split():
        singleInstance.append(word)
    trainInstances.append(singleInstance)
    singleInstance = []

# For test file:
testAttr = []
line = testFile.readline()

for word in line.split():
     testAttr.append(word)
    
testInstances = []
singleInstance = []

for line in testFile:
    for word in line.split():
        singleInstance.append(word)
    testInstances.append(singleInstance)
    singleInstance = []

def naiveBayes(cond, attributes, instances):
    firstClassValue, firstAttrValue = (0, 0)
    secondClassValue, secondAttrValue = (1, 1)

    def train():
        class1 = []
        class2 = []
        data1 = []
        data2 = []
        singleClassifier = []

        ### Separate data based on class value ###
        lastColumn = len(instances[0])-1
        firstClassValue = instances[0][lastColumn]

        for x in range(len(instances)):
            if firstClassValue == instances[x][lastColumn]:
                class1.append(instances[x])
            else:
                secondClassValue = instances[x][lastColumn]
                class2.append(instances[x])
        
        
        ### Compute for first class ###
        firstAttrValue = class1[0][0]

        for i in range(len(class1[0])):
            counter1, counter2 = 0, 0
            for j in range(len(class1)):
                if firstAttrValue == class1[j][i]:
                    counter1 = counter1 + 1
                    
                else:
                    counter2 = counter2 + 1
                    secondAttrValue = class1[j][i]
                    
            data1.append(counter1)
            data2.append(counter2)

        ### Compute first classifier ###
        for x in range(len(class1[0])-1):
            prob = data1[x]/(data1[x]+data2[x])
            prob = "{:.2f}".format(prob)
            singleClassifier.append(attributes[x])
            singleClassifier.append(firstAttrValue)
            singleClassifier.append(firstClassValue)
            singleClassifier.append(prob)
            classifier1.append(singleClassifier)
            singleClassifier = []

            prob = data2[x]/(data1[x]+data2[x])
            prob = "{:.2f}".format(prob)
            singleClassifier.append(attributes[x])
            singleClassifier.append(secondAttrValue)
            singleClassifier.append(firstClassValue)
            singleClassifier.append(prob)
            classifier1.append(singleClassifier)
            singleClassifier = []

        ### Compute for second class ###
        data1 = []
        data2 = []
        firstAttrValue = class2[0][0]

        for i in range(len(class2[0])):
            counter1, counter2 = 0, 0
            for j in range(len(class2)):
                if firstAttrValue == class2[j][i]:
                    counter1 = counter1 + 1
                    
                else:
                    secondAttrValue = class2[j][i]
                    counter2 = counter2 + 1
      
            data1.append(counter1)
            data2.append(counter2)

        ### Compute second classifier ###

        for x in range(len(class2[0])-1):
            prob = data1[x]/(data1[x]+data2[x])
            prob = "{:.2f}".format(prob)
            singleClassifier.append(attributes[x])
            singleClassifier.append(firstAttrValue)
            singleClassifier.append(secondClassValue)
            singleClassifier.append(prob)
            classifier2.append(singleClassifier)
            singleClassifier = []

            prob = data2[x]/(data1[x]+data2[x])
            prob = "{:.2f}".format(prob)
            singleClassifier.append(attributes[x])
            singleClassifier.append(secondAttrValue)
            singleClassifier.append(secondClassValue)
            singleClassifier.append(prob)
            classifier2.append(singleClassifier)
            singleClassifier = []

        #print("First classifier: ")
        print("P(class=" + str(firstClassValue) + ")=" + str(round(len(class1) / (len(class2) + len(class1)), 2)), end = " ")
        for x in classifier1:
            print("P(" + str(x[0]) + "=" + str(x[1]) + "|" + str(x[2]) + ")=" + str(x[3]), end = " ")
        print()
        #print("Second classifier: ")
        print("P(class=" + str(secondClassValue) + ")=" + str(round(len(class2) / (len(class2) + len(class1)), 2)), end = " ")
        for x in classifier2:
            print("P(" + str(x[0]) + "=" + str(x[1]) + "|" + str(x[2]) + ")=" + str(x[3]), end = " ")

        prob = len(class1) / (len(class2) + len(class1))
        classifier1.append(prob)
        prob = len(class2) / (len(class2) + len(class1))
        classifier2.append(prob)

    def test():
        accuracy = 0

        for i in range(len(instances)):
            acc1, acc2 = (1, 1)
            x = 0
            for j in range(len(instances[0])-1):
                value = instances[i][j]

                if classifier1[x][1] == value:
                    acc1 = acc1 * float(classifier1[x][3])
                if classifier1[x+1][1] == value:
                    acc1 = acc1 * float(classifier1[x+1][3])
                if classifier2[x][1] == value:
                    acc2 = acc2 * float(classifier2[x][3])
                if classifier2[x+1][1] == value:
                    acc2 = acc2 * float(classifier2[x+1][3])

                x = x+2

            acc1 = acc1 * classifier1[len(classifier1)-1]
            acc2 = acc2 * classifier2[len(classifier2)-1]

            if acc1 > acc2:
                if instances[i][len(instances[0])-1] == classifier1[0][1]:
                    accuracy = accuracy + 1
            elif acc2 > acc1:
                if instances[i][len(instances[0])-1] == classifier1[0][1]:
                    accuracy = accuracy + 1
            else:
                accuracy = accuracy + 1
        
        accuracy = (round(accuracy / len(instances), 1)*100)

        if cond == 0:
            print("\n")
            print("Accuracy on training set (" + str(len(instances)) + " instances): " + str(accuracy) + "%")
        else:
            print("Accuracy on test set (" + str(len(instances)) + " instances): " + str(accuracy) + "%")

    if cond == 0:
        train()
        test()
    else:
        test()

naiveBayes(0, trainAttr, trainInstances)
naiveBayes(1, testAttr, testInstances)