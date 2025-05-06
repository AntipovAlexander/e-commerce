# scripts/install-hooks.sh
#!/bin/bash
cp scripts/git-hooks/pre-commit-hook .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
