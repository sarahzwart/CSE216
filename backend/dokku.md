## App Name : team-Margaritavillians

## To start the app
1. Enable CORS:
    * `ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'config:set team-margaritavillians CORS_ENABLED=TRUE'`
2. Start the app
    * `ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'ps:start team-margaritavillians'`

## Stop the app please
1. Stop CORS
    * `ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'config:set team-margaritavillians CORS_ENABLED=FALSE'`
2. Stop app
    * `ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'ps:stop team-margaritavillians'`


## For a quick reset
* `ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'ps:restart team-margaritavillians'`