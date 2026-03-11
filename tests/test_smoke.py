from pathlib import Path


def test_repository_has_readme():
    assert Path("README.md").exists()


def test_release_script_exists():
    script = Path("scripts/create_release.sh")
    assert script.exists()
    content = script.read_text(encoding="utf-8")
    assert "git -C" in content
