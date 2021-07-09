import sys
import re

numState = (int)(sys.argv[1])
numAction = sys.argv[2]
inFile = open(sys.argv[3])
discFactor = sys.argv[4]

MDPstates = []

for line in inFile:
    singleState = []
    counter = 0
    word = line.split()
    while (counter < len(word)):
        if counter <= 1:
            singleState.append(word[counter])
            counter += 1
        else:
            action = []
            action.append(re.sub(r'[()]', '', word[counter]))
            action.append(re.sub(r'[()]', '', word[counter+1]))
            action.append(re.sub(r'[()]', '', word[counter+2]))
            singleState.append(action)
            counter += 3
    
    MDPstates.append(singleState)


def bellmanEq(reward, discFactor, SumDiscReward):
    r = (float)(reward)
    g = (float)(discFactor) #gamma
    J = SumDiscReward #Probability already included
    result = r + g * J
    return result

def computeJtable(MDPstates, discFactor):
    states = MDPstates
    jTable = []
    discFactor = discFactor
        
    for t in range(21):
        if t == 0:
            jValuesAtT = []
            for i in range(len(states)):
                jValuesAtT.append(states[i][0])
            jTable.append(jValuesAtT)
        elif t == 1:
            jValuesAtT = []
            for i in range(len(states)):
                jValuesAtT.append(states[i][1])
            jTable.append(jValuesAtT)
        else:
            jValuesAtT = []
            for i in range(len(states)):
                x = 2
                bestAction = ""
                bestActionValue = -100000
                while (x < len(states[i])):
                    action = states[i][x][0]
                    state = states[i][x][1]
                    prob = states[i][x][2]
                    column = jTable[0].index(state)
                    jValue = jTable[t-1][column][0]
                    actionValue = (float)(prob) * (float)(jValue)
                    if actionValue > bestActionValue:
                        bestActionValue = actionValue
                        bestAction = action
                    x += 1
                JstarValue = bellmanEq(states[i][1], discFactor, bestActionValue)
                bestPolicy = [JstarValue, bestAction]
                jValuesAtT.append(bestPolicy)
            
            jTable.append(jValuesAtT) 

    for i in range(20):
        print("After iteration " + (str)(i) + ":  " + (str)(jTable[i]))



print(computeJtable(MDPstates, discFactor))