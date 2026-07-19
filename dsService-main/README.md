# dsService

# 🐍 Python Env & Build Commands (dsService)

Create Virtual Environment
python -m venv dsenv

Creates isolated Python environment.

Activate (Git Bash / MINGW64)
source dsenv/Scripts/activate

Activates environment. (dsenv) appears in terminal.

Verify Environment
which python

Confirms Python path is inside dsenv.

echo $VIRTUAL_ENV

Shows active virtual env path.

Upgrade pip
python -m pip install --upgrade pip

Prevents dependency/install issues.

Install Dependencies
pip install langchain langchain-openai langchain-core langchain-community

Installs LLM + LangChain libraries.

pip install flask

Installs Flask for API server.

pip install setuptools

Required for packaging.

Build Source Distribution
python setup.py sdist

Creates distributable package inside /dist.
