# Metrics

1. **Stabilité (I)** :
   - **Définition** : La stabilité d'un package mesure la fréquence à laquelle le code source de ce package est modifié. Un package est considéré comme stable s'il subit rarement des modifications, ce qui signifie qu'il fournit une base fiable pour d'autres parties du système.
   - **Formule** : I = (Nombre de dépendances entrantes) / (Nombre total de dépendances)
   - **Impact** : Les packages plus stables sont moins sujets aux changements, ce qui facilite la maintenance du code et réduit les risques d'effets secondaires lors de la modification d'autres parties du système.

2. **Abstractité (A)** :
   - **Définition** : L'abstractité d'un package mesure dans quelle mesure il est conçu de manière abstraite, c'est-à-dire à quel point il est basé sur des concepts généraux plutôt que sur des détails d'implémentation spécifiques.
   - **Formule** : A = (Nombre d'interfaces ou de classes abstraites) / (Nombre total de classes publiques)
   - **Impact** : Les packages plus abstraits sont plus flexibles et extensibles, permettant aux autres parties du système de s'intégrer facilement et de modifier les implémentations internes sans modifier les utilisations externes.

3. **Distance de la séquence principale (D)** :
   - **Définition** : La distance de la séquence principale d'un package mesure à quel point il se situe par rapport à la "main sequence" théorique où la somme de l'abstractité et de la stabilité est égale à 1. Les valeurs proches de zéro indiquent un respect du SAP(Stable-Abstractions-Principle).
   - **Formule** : D = |A + I - 1|
   - **Impact** : Cette métrique permet d'évaluer à quel point un package respecte le SAP, avec des valeurs proches de zéro indiquant un bon équilibre entre abstractité et stabilité.

4. **Distance de la séquence principale normalisée (DN)** :
   - **Définition** : La distance de la séquence principale normalisée d'un package est une variation de la métrique D qui a été normalisée pour varier entre 0 et 1.
   - **Formule** : DN = |A + I - 1|
   - **Impact** : Cette métrique permet une évaluation plus facile de l'adhésion au SAP, avec des valeurs proches de zéro indiquant un bon respect du SAP.

5. **Nombre d'Enfants d'une Classe (NOC)**

   - **Définition** : Le nombre d'enfants d'une classe (NOC) est une mesure qui indique le nombre de sous-classes directes héritant d'une classe parente dans une hiérarchie d'héritage.

   - **Formule** : Le NOC d'une classe est simplement le nombre de ses sous-classes directes.

<<<<<<< HEAD
- **Impact** : 
    - Un NOC élevé peut indiquer une classe centrale dans la hiérarchie d'héritage, ayant une influence significative sur la conception globale du système.
    - Il peut nécessiter des tests supplémentaires pour valider les méthodes héritées dans chaque sous-classe.
    - Un NOC élevé peut également indiquer une mauvaise abstraction de la classe parente, nécessitant une révision de la conception pour une meilleure distribution des responsabilités.
=======
   - **Impact** : 
  - Un NOC élevé peut indiquer une classe centrale dans la hiérarchie d'héritage, ayant une influence significative sur la conception globale du système.
  - Il peut nécessiter des tests supplémentaires pour valider les méthodes héritées dans chaque sous-classe.
  - Un NOC élevé peut également indiquer une mauvaise abstraction de la classe parente, nécessitant une révision de la conception pour une meilleure distribution des responsabilités.
>>>>>>> refs/remotes/origin/main

6. **Nombre de Parents (NOPa)** :
   - **Définition** : Le nombre de parents d'une classe (NOPa) est le nombre de classes dont elle hérite directement.
   - **Formule** : NOPa = Nombre de superclasses directes de la classe.
   - **Impact** :
     - Un NOPa élevé peut indiquer une classe qui hérite de multiples fonctionnalités, ce qui peut entraîner une complexité accrue et une plus grande dépendance par rapport à d'autres parties du système.
     - Cela peut nécessiter une analyse plus approfondie de la hiérarchie d'héritage pour s'assurer que la classe reste cohérente et bien définie dans son rôle.

7. **Nombre de Descendants (NOD)** :
   - **Définition** : Le nombre de descendants d'une classe (NOD) est le nombre de classes qui héritent directement ou indirectement de cette classe.
   - **Formule** : NOD = Nombre de sous-classes directes + Nombre de sous-classes indirectes.
   - **Impact** :
     - Un NOD élevé peut indiquer une classe qui a une influence étendue sur différentes parties du système, en raison de ses nombreux descendants.
     - Cela peut nécessiter une attention particulière lors de la modification de la classe parente pour éviter des effets indésirables sur ses descendants.

8. **Nombre d'Ancêtres (NOA)** :
   - **Définition** : Le nombre d'ancêtres d'une classe (NOA) est le nombre de classes à partir desquelles elle hérite directement ou indirectement.
   - **Formule** : NOA = Nombre de superclasses directes + Nombre de superclasses indirectes.
   - **Impact** :
     - Un NOA élevé peut indiquer une classe qui hérite de nombreuses fonctionnalités et qui peut être influencée par divers aspects de la conception du système.
     - Cela peut rendre la classe plus complexe à comprendre et à maintenir, nécessitant une gestion plus minutieuse de ses interactions avec ses ancêtres.

9. **Nombre de Liens (NOL)** :
   - **Définition** : Le nombre de liens d'une classe (NOL) est le nombre total de relations de dépendance (agrégation, composition, association, etc.) qu'elle a avec d'autres classes.
   - **Formule** : NOL = Nombre total de relations de dépendance.
   - **Impact** :
     - Un NOL élevé peut indiquer une classe qui est fortement liée à d'autres parties du système, ce qui peut augmenter la complexité et la dépendance entre les composants.
     - Cela peut rendre la classe plus difficile à isoler et à réutiliser, nécessitant une gestion attentive de ses interactions avec d'autres classes.

10. **Nested Block Depth (NBD)**

    - **Définition** : La profondeur des blocs imbriqués (NBD) est le nombre maximal de niveaux d'imbrication de blocs de code à l'intérieur d'une fonction ou d'une méthode.
  
    - **Formule** : La NBD est déterminée en comptant le nombre maximal de blocs de code qui sont imbriqués les uns à l'intérieur des autres à l'intérieur d'une fonction ou d'une méthode.

    - **Impact** :
    - Une NBD élevée peut rendre le code source difficile à lire, à comprendre et à maintenir.
    - Une profondeur excessive des blocs imbriqués peut indiquer une complexité excessive du code, ce qui peut augmenter le risque d'erreurs et de bogues.
    - Une NBD élevée peut également rendre le code plus difficile à tester, car il peut y avoir plusieurs chemins d'exécution à travers les blocs imbriqués, augmentant ainsi la complexité des tests unitaires.

