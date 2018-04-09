# About
SidebarVotes lets you make votes inspired by CS:GO utilizing the sidebar. The only vote applications currently availible is a **VoteKick** and a test vote for debugging. (Will be removed when out of beta)
The plugin is still in beta but runs flawlessly. It comes with a highly customizeable config.yml that allows you to completely change the colors and text used.
SidebarVotes also acts as a library to create your own vote applications extremely easily. Tutorials can be found in the wiki page

**Please suggest your vote ideas! I will realize them as soon as possible.**

### How it works:
A player calls a vote. For instance, a kick vote to kick the player *Foo*
Every player will be notified in the chat and gets to vote Yes or No.
On the right sidebar you can see the amount of votes. Voting Yes increases the value by one and No does the opposite (Negative values are possible)
If the value hits the required minimum (Player-count / 2), the vote will pass and the player is being kicked.
The vote can only fail, if the time runs out. The default time is 5 minutes but can be changed in the config.yml

**Note:** Admins can bypass the voting progress and accept or deny the vote with **/vote-pass** and **/vote-deny**

Thank you for taking your time to read through this. I hope you will use my plugin in your own servers.
