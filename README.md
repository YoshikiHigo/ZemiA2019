# ZemiA2019
ソースコードを読んで分からないことがあったら私に連絡お願いします.

---

## 次の内容を追加.
- 大まかなデータ構造の決定,
- ZemiAMain:
主な処理 (構文木の構成 etc. )を中心に行う（予定）
- ZemiAVisitor:
構文木のノードをたどる. ここでは測ったメトリクスを一時的に保管したい.
- MetricsCompute:
計算系メトリクスを定義したヘルパークラス. メソッドのみを定義.
- ElementInfo:
ClassInfo, AttributeInfo, MethodInfo の共通部分を抽象化したクラス.
- ClassInfo:
あるクラスの情報を保存. visitorによる計測ののち, クラス関係のメトリクス（尺度）をここに保存したい.
- AttributeInfo:
ある属性の情報を保存. visitorによる計測ののち, 属性関係のメトリクス（尺度）をここに保存したい.
- MethodInfo:
あるメソッドの情報を保存. visitorによる計測ののち, メソッド関係のメトリクス（尺度）をここに保存したい.
- HelperToString:
ClassInfo, AttributeInfo, MethodInfo の toString の実装を補助するヘルパークラス. 各クラスの toString の形式を最終的な標準出力の1行にしたいと考えている.

---

- 計算を用いて測るメトリクス (AWM etc.) はおそらく実装完了.

- カウントして計測するメトリクスは ZemiAVisit.visit を利用するものと思われる. （試しに cyclo とnom を実装）

- cc, cdisp 以外のメトリクスの意味の下調べを完了. ZemiAVisitor, MetricsCompute 内で簡潔に記している.

- 各クラスの toString で定義した形式（formatメソッド参照）を最終的な標準出力の1行にしたいと考えている.