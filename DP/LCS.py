def LCS(str1,str2):

        l1,l2 = len(str1),len(str2)

        dp = [[0 for i in xrange(l2+1)] for j in xrange(l1+1)]

        for i in xrange(1,l1+1):
                for j in xrange(1,l2+1):
                        if str1[i-1] == str2[j-1]:
                                dp[i][j] = dp[i-1][j-1] + 1
                        else:
                                dp[i][j] = max(dp[i-1][j],dp[i][j-1])

        return dp[-1][-1]
