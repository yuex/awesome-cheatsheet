
# Draw Picture

    def draw12(e1,e2):
        import matplotlib.pyplot as plt
        width = len(e1)

        fig = plt.figure(figsize=(16,6))

        plt.subplot(1,2,1)
        plt.plot(xrange(1,1+width), e1, 'b-', label="Brute Force")
        plt.legend(loc='lower right')
        plt.axis([1-.05*width, 1+1.05*width,-.05,1.05])
        plt.xlabel("Number of Samples")
        plt.ylabel("Estimates of Cond. Prob.")
        plt.title("Brute Force")
        plt.grid()

        plt.subplot(1,2,2)
        plt.plot(xrange(1,1+width), e1, 'b-', label="Brute Force")
        plt.plot(xrange(1,1+width), e2, 'r-', label="Gibbs")
        plt.legend(loc='lower right')
        plt.axis([1-.05*width, 1+1.05*width,-.05,1.05])
        plt.xlabel("Number of Samples")
        plt.ylabel("Estimates of Cond. Prob.")
        plt.title("Brute Force vs Gibbs")
        plt.grid()
        plt.grid()
        plt.grid()

        plt.savefig("pplot")

# Numpy

    import numpy as np
    M = np.array([[0,1],[1,0]])

    M[np.where(M == 1)] = 2
    M[:,0], M[0,:]

    M.shape
    M.reshape((1,4)) # mat.reshape : int x int
    M.reshape((4,))
    M.reshape((,4))
    M[0] = 10 # can assign to original matrix directly

    M.T : mat -> mat.T

    np.max : mat(n,m) -> val
    np.maximum : mat(n,m) -> mat(n,m) -> mat(n,m)

    np.zeros : int x int
    np.ones  : int x int

    np.(*) : mat(n,m) -> mat(n,m) -> mat(n,m)
    np.dot : mat(n,k) -> mat(k,m) -> mat(n,m)

    np.true_divide : int -> int -> float
