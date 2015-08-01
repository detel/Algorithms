def insertionsToFormPalin(string):
        n = len(string)
        dp = [[0]*n for _ in xrange(n)]

        for gap in xrange(1,n):
                l = 0
                for h in xrange(gap,n):
                        if string[l] == string[h]:
                                dp[l][h] = dp[l+1][h-1]
                        else:
                                dp[l][h] = 1 + min(dp[l+1][h],dp[l][h-1])
                        l += 1

        return dp[0][-1]

print insertionsToFormPalin("deepit")
