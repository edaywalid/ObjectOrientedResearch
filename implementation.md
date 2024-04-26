

## Variable naming coherence :
    calcule la coherence de denomination de tout les variable dans une program
        VNC = product{NameCoherenceFactor(name)}   //  0<= NameCoherenceFactor(x) <= 1

## Methode naming coherence :
    calcule la coherence de denomination de tout les methode dans une program
        MNC = product{MethodCoherenceFactor(methode)}   //  0<= MethodCoherenceFactor(x) <= 1


## Class naming coherence :
    calcule la coherence de denomination de tout les class dans un program
        VNC = product{ClassCoherenceFactor(Class)}   //  0<= ClassCoherenceFactor(x) <= 1


## Component naming coherence :
    calcule la coherence de denomination de tout les composent dans un program
        CMNC = CNC.MCN.VNC

    impact : -ecrire des programmes robustes est facilement maintenable.
## Nubmber of imports per class :
    calcule le nombre total de class importer dans une class
        NIC = sum{imports}

    impact : estimation de la charge total sur le Linker(phase compilation)
                 ++grand ++chargee

## Number of non effectif imports :
    calcule le nombre d'imports non utiliser dans une class
        NNEIC = sum{nonUsedImports}
    impact : elimination des tranches de code non necessaire, maintenabilite.
## Number of effectif imports per class
    calcule le nombre total de class importer effectivement utiliser dans un class
        NEIC = NIC - NNEIC

    impact : estimation de la charge total effectif sur le Linker(phase compilation)
                 ++grand ++chargee

## Time reading class :
    calcule le temps approximative de la lecture de la class par le compilateur
        time = system.nanotime(); a la fin de chaque class

    impact : estimation du temps de compilation , maintenabilite , facilite le test et le debugage
